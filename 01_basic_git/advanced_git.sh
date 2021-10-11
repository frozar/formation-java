#!/bin/sh

# Documentation link:
# https://www.youtube.com/watch?v=0SJCYPsef54&ab_channel=DmitriSnytkine

echo "toto" | git hash-object -w --stdin

## Pretty print of the content of an object: content of the file
git cat-file -p "<hash_object>"

## Print the type of the object: a blob
git cat-file -t "<hash_object>"

git update-index --add --cacheinfo 100644 "<hash_object>" "<filename>"

git status

git write-tree

## Print the type of the object: a tree
git cat-file -t "<hash_object_tree>"

## Pretty print of the content of an object: content of the tree
git cat-file -p "<hash_object_tree>"

git status

git commit-tree "<hash_object_tree>" -m "Crazy commit"

git status

git update-ref refs/heads/master "<hash_object_tree>"

## git log finally works

git checkout HEAD -- "<filename>"

## The hardest way to create a file with git
