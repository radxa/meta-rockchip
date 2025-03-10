# meta-rockchip

Yocto BSP layer for Rockchip-bsed deivces using upstream sources as much as possible.
This project is based on upstream - https://git.yoctoproject.org/meta-rockchip

## Build Host

To install the required packages on a Debian/Ubuntu run

```
$ sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential \
    chrpath socat cpio python3 python3-pip python3-pexpect xz-utils \
    debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa \
    libsdl1.2-dev pylint xterm python3-subunit mesa-common-dev zstd liblz4-tool
```

## Configure Yocto/OE

In order to build an image, you need to download some layers.

```
~ $ mkdir yocto; cd yocto
~/yocto $ git clone git://git.openembedded.org/bitbake -b 2.8
~/yocto $ git clone git://git.openembedded.org/openembedded-core -b scarthgap
~/yocto $ git clone git://git.yoctoproject.org/meta-arm -b scarthgap
~/yocto $ git clone git://git.openembedded.org/meta-openembedded -b scarthgap
~/yocto $ git clone git@github.com:radxa/meta-rockchip.git -b scarthgap
~/yocto $ git clone git://git.yoctoproject.org/meta-virtualization -b scarthgap
```

Then you need to source the configuration script.

```
~/yocto $ source openembedded-core/oe-init-build-env
```

Add some layers to build/conf/bblayers.conf

For example:

```
# conf/bblayers.conf
BBLAYERS ?= " \
  ${TOPDIR}/../openembedded-core/meta \
  ${TOPDIR}/../meta-arm/meta-arm \
  ${TOPDIR}/../meta-arm/meta-arm-toolchain \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-rockchip \
  ${TOPDIR}/../meta-openembedded/meta-filesystems \
  ${TOPDIR}/../meta-virtualization \
  "
```

Add more configuration to Yocto image like

```
# Enable systemd
INIT_MANAGER = "systemd"

DISTRO_FEATURES:append = " virtualization"
IMAGE_INSTALL:append = " \
    alsa-utils \
    bluez5 \
    e2fsprogs \
    evtest \
    canutils \
    docker-compose \
    gptfdisk \
    i2c-tools \
    iperf3 \
    iw \
    kernel-modules \
    linux-firmware \
    minicom \
    packagegroup-core-full-cmdline \
    parted \
    pciutils \
    ppp \
    usbutils \
    wpa-supplicant \
    libgpiod \
    libgpiod-dev \
    libgpiod-tools \
"
```

## Building meta- BSP Layers

Now youou should be able to build a image as such:

```
MACHINE=rock-5b bitbake core-image-full-cmdline
```

All supported machines can be found in meta-rockchip/conf/machine.

At the end of a successful build, you should have an .wic image in /path/to/yocto/build/tmp-glibc/deploy/\<MACHINE\>/

If you want to boot the image on microSD card the follow below steps.

```
cd tmp-glibc/deploy/images/\<MACHINE\>
sudo dd if=./core-image-full-cmdline-rock-5b.rootfs.wic of=/dev/sdX
```
