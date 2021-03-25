#!/bin/bash
npm config set registry https://registry.npm.taobao.org --global
npm config set disturl https://npm.taobao.org/dist --global
npm install
chmod +x /app/bin/www
/app/bin/www