#!/usr/bin/env python3
"""Validate Android English/Bangla resource keys and format placeholders."""

from collections import Counter
from pathlib import Path
import re
import sys
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parents[1]
RESOURCE_FILES = {
    "English": ROOT / "app/src/main/res/values/strings.xml",
    "Bangla": ROOT / "app/src/main/res/values-bn/strings.xml",
}
PLACEHOLDER = re.compile(r"%(\d+)\$[sd]")


def load_resources(path: Path) -> dict[str, ET.Element]:
    try:
        root = ET.parse(path).getroot()
    except (ET.ParseError, OSError) as error:
        raise SystemExit(f"ERROR: Cannot parse {path.relative_to(ROOT)}: {error}") from error

    resources: dict[str, ET.Element] = {}
    names: list[str] = []
    for element in root:
        name = element.attrib.get("name")
        if name is None:
            continue
        names.append(name)
        resources.setdefault(name, element)

    duplicates = sorted(name for name, count in Counter(names).items() if count > 1)
    if duplicates:
        print(f"ERROR: Duplicate resource names in {path.relative_to(ROOT)}: {', '.join(duplicates)}")
    return resources


def placeholders(element: ET.Element) -> Counter[str]:
    # Android resources may contain escaped newline sequences and nested tags.
    text = "".join(element.itertext())
    return Counter(PLACEHOLDER.findall(text))


def main() -> int:
    resources = {
        locale: load_resources(path)
        for locale, path in RESOURCE_FILES.items()
    }
    failed = False

    english = set(resources["English"])
    bangla = set(resources["Bangla"])
    missing_bangla = sorted(english - bangla)
    missing_english = sorted(bangla - english)

    if missing_bangla:
        failed = True
        print(f"ERROR: Missing Bangla resources: {', '.join(missing_bangla)}")
    if missing_english:
        failed = True
        print(f"ERROR: Missing English resources: {', '.join(missing_english)}")

    for name in sorted(english & bangla):
        en_placeholders = placeholders(resources["English"][name])
        bn_placeholders = placeholders(resources["Bangla"][name])
        if en_placeholders != bn_placeholders:
            failed = True
            print(
                f"ERROR: Format placeholders differ for '{name}': "
                f"English={dict(en_placeholders)}, Bangla={dict(bn_placeholders)}"
            )

    if failed:
        return 1

    print(
        f"OK: English and Bangla resource keys and format placeholders match "
        f"({len(english)} keys; no duplicates)."
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
