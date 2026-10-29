#!/usr/bin/env python3
"""Check that English and Bangla Android string resources stay in sync."""

from collections import Counter
from pathlib import Path
import sys
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parents[1]
RESOURCE_FILES = {
    "English": ROOT / "app/src/main/res/values/strings.xml",
    "Bangla": ROOT / "app/src/main/res/values-bn/strings.xml",
}


def load_names(path: Path) -> list[str]:
    try:
        root = ET.parse(path).getroot()
    except (ET.ParseError, OSError) as error:
        raise SystemExit(f"ERROR: Cannot parse {path.relative_to(ROOT)}: {error}") from error

    # Compare all named resources, not only <string>, so arrays/plurals added
    # later are also required in both supported locales.
    return [
        element.attrib["name"]
        for element in root
        if "name" in element.attrib
    ]


def main() -> int:
    names = {locale: load_names(path) for locale, path in RESOURCE_FILES.items()}
    failed = False

    for locale, locale_names in names.items():
        duplicates = sorted(name for name, count in Counter(locale_names).items() if count > 1)
        if duplicates:
            failed = True
            print(f"ERROR: Duplicate resource names in {locale}: {', '.join(duplicates)}")

    english = set(names["English"])
    bangla = set(names["Bangla"])
    missing_bangla = sorted(english - bangla)
    missing_english = sorted(bangla - english)

    if missing_bangla:
        failed = True
        print(f"ERROR: Missing Bangla resources: {', '.join(missing_bangla)}")
    if missing_english:
        failed = True
        print(f"ERROR: Missing English resources: {', '.join(missing_english)}")

    if failed:
        return 1

    print(f"OK: English and Bangla resource keys match ({len(english)} keys; no duplicates).")
    return 0


if __name__ == "__main__":
    sys.exit(main())
