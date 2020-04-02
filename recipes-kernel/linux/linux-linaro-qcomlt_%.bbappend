FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

KERNEL_CONFIG_FRAGMENTS_append = " \
				  ${WORKDIR}/iwd-wlan.cfg \
				  ${WORKDIR}/disable-sound.cfg \
				  ${WORKDIR}/disable-bt.cfg \
				  ${WORKDIR}/disable-graphics.cfg \
				 "


SRC_URI_append = "\
		  file://iwd-wlan.cfg \
		  file://disable-sound.cfg \
		  file://disable-bt.cfg \
		  file://disable-graphics.cfg \
	         "
