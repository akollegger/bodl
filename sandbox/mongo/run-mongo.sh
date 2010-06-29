#!/usr/bin/env bash

MONGODIR="/opt/verylocal/mongodb-osx-x86_64-2010-04-17/"

${MONGODIR}/bin/mongod --dbpath="/srv/mongodb/data/"

