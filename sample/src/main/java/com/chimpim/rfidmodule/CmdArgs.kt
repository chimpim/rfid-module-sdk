package com.chimpim.rfidmodule

import org.kohsuke.args4j.Option

class CmdArgs {

    @Option(
            name = "-d",
            aliases = ["--address"],
            metaVar = "<String>",
            usage = "-a 192.168.1.100",
            help = true
    )
    var address: String = "192.168.1.100"

    @Option(
            name = "-rt",
            aliases = ["--rtype"],
            metaVar = "<Int>",
            usage = "--rt 4",
            help = true
    )
    var rtype: Int = 4


    @Option(
            name = "-n",
            aliases = ["--native"],
            metaVar = "<String>",
            usage = "--native jni",
            help = true
    )
    var native: String = "jni"


    @Option(
            name = "-support",
            aliases = ["--support"],
            metaVar = "<Boolean>",
            usage = "--support",
            help = true
    )
    var support: Boolean = false

    @Option(
            name = "-a",
            aliases = ["--ants"],
            metaVar = "<IntArray>",
            usage = "-a 1 2 3 4",
            help = true
    )
    var ants: String = "1 2 3 4"
    val antArray: IntArray
        get() = ants.split(" ").map { it.toInt() }.toIntArray()
}
