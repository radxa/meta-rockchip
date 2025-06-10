# Copyright (C) 2020, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Rockchip ALSA config files"
SECTION = "multimedia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"

inherit local-git

SRC_URI = " \
	git://gitlab.com/rockchip_linux_sdk_6.1/linux/external/alsa-config.git;protocol=https;branch=master; \
"
SRCREV = "5529257039c41955724c1069796f5cb7b2015c77"
S = "${WORKDIR}/git"

inherit meson

FILES:${PN} = "*"
