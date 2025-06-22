#!/bin/bash

mkdir -p resources

python misc/text-asset-decoder/decoder.py
cp -r misc/text-asset-decoder/output resources/msm-text