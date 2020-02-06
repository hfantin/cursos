#!/bin/bash

function main(){
    echo "> Executa build em todos os projetos"
    for dir in */; do
      echo "> $dir"
      if [[ -e "$dir/.gradle" ]]; then
        cd $dir
        ./gradlew clean assemble
        cd ..
      fi
    done
}

main
