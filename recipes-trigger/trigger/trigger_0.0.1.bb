LICENSE = "CLOSED"

SRC_URI = "file://trigger.service \
	   file://trigger.sh \
	   "

SYSTEMD_SERVICE_${PN} = "trigger.service"

inherit systemd


do_install() {
  install -m 0755 -d ${D}${systemd_unitdir}/system
  install -m 0755 -d ${D}${bindir}
  install -m 0755 ${WORKDIR}/trigger.sh ${D}${bindir}
  install -m 644 ${WORKDIR}/trigger.service  ${D}${systemd_unitdir}/system/
  
}


FILES_${PN} += "${systemd_unitdir}/system/* \
	        ${bindir}/trigger.sh \
	       "
