/**
 * Created by wangjunfei on 4/14/15.
 */

import com.netaporter.uri.Uri


import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import com.netaporter.uri.dsl._

class LogAnalyze extends App {


  class Log( val phpServer: String, val timeStamp: String, val url: String, val response: String) {}

  private val logs = new mutable.HashMap[String ,mutable.ArrayBuffer[Log]];
  private var source: Source = null;

  if (args.length > 0) {
    val filePath = args(0)
    source = Source.fromFile(filePath)
    println("read file :"+filePath)


  } else
    println(" please specify file path")

  def scanFile = {
    for (line <- source.getLines()) {

      val fieldAry = line.split("\t")
      val fieldUrl: Uri = fieldAry(6)
      val log = new Log(fieldAry(7), fieldAry(1), fieldAry(6), fieldAry(5))
      val uid=fieldUrl.query.paramMap.getOrElse("qyid", "").toString

      if(logs.contains(uid))
           logs.get(uid).get+=log
      else {
         val logList=new ArrayBuffer[Log]()
        logList+=log
        logs.put(uid,logList)

      }

      val sort_map = new mutable.HashMap[String ,mutable.ArrayBuffer[Log]];

       for((uid,logList) <- logs) {
         sort_map.put(uid, logList.sortWith(_.timeStamp < _.timeStamp))
         print(uid, logList.size)
       }


    }
  }

}
