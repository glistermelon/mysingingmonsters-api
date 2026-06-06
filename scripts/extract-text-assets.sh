#!/bin/bash

set -e

[ -d text-assets ] && rm -r text-assets

target_file=""

for file in *.xapk; do
    if [[ -f "$file" ]]; then
        target_file="$file"
        break
    fi
done

if [[ -n "$target_file" ]]; then
    echo "Unzipping: $target_file"
else
    echo "No xapk found"
fi

unzip "$target_file" -d xapk
unzip xapk/asset_pack.apk -d xapk/asset_pack

cp -r xapk/asset_pack/assets/text text-assets

rm -r xapk