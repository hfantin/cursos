# Udemy course [Working with Microservices in Go (Golang)](https://www.udemy.com/course/working-with-microservices-in-go)
## Links
- [shutdown gracefully](https://medium.com/@pinkudebnath/graceful-shutdown-of-golang-servers-using-context-and-os-signals-cc1fa2c55e97)
- [go tools ](https://github.com/tsawler/toolbox)

## How to mount a volume on podman
from this [link](https://stackoverflow.com/a/71542236)   
1. podman machine stop podman-machine-default
2. podman machine rm podman-machine-default
3. podman machine init -v $HOME:$HOME - or /opt/desenv/workspace
3. podman machine init -v /opt/desenv/workspace:/opt/desenv/workspace
4. podman machine start
5. podman run -ti --rm -v $HOME:$HOME busybox

## Change user and group of db-data folder
>  chown -R $(whoami):staff db-data


## perminssion on podman
https://www.tutorialworks.com/podman-rootless-volumes/

podman unshare chown user:user $PATH
podman unshare chown postgres:postgres /var/lib/postgresql/data


/var/lib/postgresql/data