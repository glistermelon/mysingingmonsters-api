#!/bin/bash

mkdir -p resources

python misc/text-asset-decoder/decoder.py
cp -r misc/text-asset-decoder/output resources/msm-text

python misc/java-info-gen/monsters.py
cp misc/java-info-gen/output/monster_types.json resources