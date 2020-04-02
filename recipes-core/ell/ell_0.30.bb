SUMMARY = "Embedded Linux Library"
DESCRIPTION = "ELL is a DBUS library which provides DBUS bindings."
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"
DEPENDS = "dbus"

SRCREV = "be0e81bbbf4f68ee79c4ec0ff9c089fcef30601d"

SRC_URI = "git://git.kernel.org/pub/scm/libs/ell/ell.git"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend () {
    mkdir -p ${S}/build-aux
}
