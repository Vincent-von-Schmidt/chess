set shell := ["powershell"]

default:
  just --list

build:
  .\mvnw.cmd clean package

mutation-testing:
  ./mvnw.cmd package pitest:mutationCoverage

setup-maven-wrapper:
  chmod +x ./mvnw

reset-maven-wrapper:
  chmod -x ./mvnw
