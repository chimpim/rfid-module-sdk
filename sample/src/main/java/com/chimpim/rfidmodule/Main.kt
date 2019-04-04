package com.chimpim.rfidmodule

import com.chimpim.rfidmodule.internal.HexStringUtils
import com.chimpim.rfidmodule.internal.JnaSupport
import com.chimpim.rfidmodule.internal.JniSupport
import com.chimpim.rfidmodule.internal.Utils
import org.kohsuke.args4j.CmdLineParser

fun main(vararg args: String) {

    val cmdArgs = CmdArgs()
    val parser = CmdLineParser(cmdArgs)
    parser.printExample { true }
    try {
        parser.parseArgument(*args)
    } catch (e: Exception) {
        System.err.println("Usage: ")
        parser.printUsage(System.err)
        System.exit(0)
    }

    if (cmdArgs.support) {
        println("os.name: ${System.getProperty("os.name")}")
        println("os.arch: ${System.getProperty("os.arch")}")
        println("jniSupport: ${JniSupport.values().toList().map { it.name }.joinToString { it }}")
        println("jnaSupport: ${JnaSupport.values().toList().map { it.name }.joinToString { it }}")
        println("isSupportJniReader: ${Utils.isSupportJniReader()}")
        println("isSupportJnaReader: ${Utils.isSupportJnaReader()}")
        System.exit(0)
    }
    println(cmdArgs)

    val reader = when (cmdArgs.native) {
        "jni" -> RfidModule.newJniReader()
        "jna" -> RfidModule.newJnaReader()
        else -> {
            System.err.println("Usage: ")
            parser.printUsage(System.err)
            throw RuntimeException("--native error")
        }
    }

    var err = reader.InitReader_Notype(cmdArgs.address, cmdArgs.rtype)

    println("InitReader_Notype Err: $err")

    if (err == Reader.READER_ERR.MT_OK_ERR) {
        val tagInfos = arrayOfNulls<Reader.TAGINFO>(200)
        val tagCnt = IntArray(1)
        val timeout: Short = 100
        val ants = cmdArgs.antArray
        val antCnt = ants.size
        while (true) {
            err = reader.TagInventory(ants, antCnt, timeout, tagInfos, tagCnt)
            println("TagInventory Err: $err")
            if (err == Reader.READER_ERR.MT_OK_ERR) {
                for (i in 0 until tagCnt[0]) {
                    println(HexStringUtils.bytesToHexString(tagInfos[i]?.EpcId))
                }
            }
        }
    }

}
