/**
 * Created by wangjunfei on 4/21/15.
 */


import java.io.File
import java.util.Date

import org.apache.log4j.Logger


object FileCleaner extends App {
  val log = Logger.getLogger(FileCleaner.getClass)

  def deleteUnUsedFile(dir: File): Unit = {

    log.info("into dir :"+dir.getAbsolutePath)
    val now = new Date()
    for (file <- dir.listFiles()) {


      if (file.isFile) {
//        log.info("check file:"+file.getAbsolutePath)
        val xtFile = new javaxt.io.File(file)
        val fileLastAccessTime = xtFile.getLastAccessTime
        if (Math.abs((now.getTime - fileLastAccessTime.getTime) / (1000 * 60 * 60 * 24)) > 30)
          log.info("delete file " + file.getAbsolutePath + " atime "+fileLastAccessTime+ " with result :" + xtFile.delete());

      } else if (file.isDirectory) {
         deleteUnUsedFile(file)
      }
    }


  }
  deleteUnUsedFile(new File("/data/view/online5/"))

}
