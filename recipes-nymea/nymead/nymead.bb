DESCRIPTION = "nymead"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75c6d0a8c08698a4cd93d203ae92362e"

DEPENDS = "avahi qtbase qtwebsockets qtconnectivity nymea-mqtt nymea-remoteproxy"

SRCREV="fix-include-installs"
SRC_URI = " \
    git://github.com/guh/nymea.git;protocol=https;branch=fix-include-installs \
	file://init \
"

S = "${WORKDIR}/git"

inherit update-rc.d qmake5

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "nymead"

do_install_append() {
	install -Dm 0755 ${WORKDIR}/init ${D}${INIT_D_DIR}/nymead
}

PACKAGES += "${PN}-test"
FILES_${PN}-test = " \
    ${libdir}/nymea/plugins/libnymea_devicepluginmock.so \
	/usr/tests/* \
"

FILES_${PN}-dev += "${libdir}/nymea.pc"

RDEPENDS_${PN} += " \
   avahi-daemon \
"
