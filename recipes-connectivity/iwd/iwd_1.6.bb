DESCRIPTION = "Wireless daemon for Linux"
SECTION = "kernel/userland"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fb504b67c50331fc78734fed90fb0e09"
DEPENDS = "ell readline dbus"

SRCREV = "ad97f4f945b828425ff288e905523e11b330d41d"

SRC_URI = "git://git.kernel.org/pub/scm/network/wireless/iwd.git \
           file://iwd.conf \
           file://main.conf \
           "

S = "${WORKDIR}/git"
SYSTEMD_SERVICE_${PN} = "iwd.service \
                         ${@bb.utils.contains('PACKAGECONFIG', 'wired', 'ead.service', '', d)}"

inherit autotools pkgconfig systemd

EXTRA_OECONF += " --enable-external-ell --disable-manual-pages "

do_configure_prepend () {
    mkdir -p ${S}/build-aux
}
do_install_append() {
    mkdir --parents ${D}${docdir}/${BPN}
    install -m644 ${S}/doc/*.txt ${D}${docdir}/${BPN}
    install -m755 -d ${D}/usr/lib/tmpfiles.d
    install -m644 ${WORKDIR}/iwd.conf ${D}${libdir}/tmpfiles.d
    install -m755 -d ${D}${sysconfdir}/iwd
    install -m644 ${WORKDIR}/main.conf ${D}${sysconfdir}/iwd
}

FILES_${PN} += "${datadir}/dbus-1 \
                /usr/lib/modules-load.d/pkcs8.conf \
                /usr/share/dbus-1/system-services/net.connman.iwd.service \
                /usr/share/dbus-1/system.d/iwd-dbus.conf \
                /usr/lib/tmpfiles.d/iwd.conf \
                ${sysconfdir}/iwd \
		/lib/systemd/network/80-iwd.link \
		${sysconfdir}/iwd \
		${sysconfdir}/iwd/main.conf \
               "

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[wired] = "--enable-wired,--disable-wired"
PACKAGECONFIG[ofono] = "--enable-ofono,--disable-ofono"
PACKAGECONFIG[systemd] = "--with-systemd-unitdir=${systemd_system_unitdir},--disable-systemd-service,systemd"
