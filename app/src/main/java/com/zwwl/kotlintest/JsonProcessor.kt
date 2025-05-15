package com.zwwl.kotlintest

import android.content.Context
import com.alibaba.fastjson.JSON
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

object JsonProcessor {

    // 定义实体类
    data class DevBean(val dev: String, val imei: String)

    fun processAssetsJson(context: Context, fileName: String) : MutableSet<String> {
        try {
            // 1. 读取assets文件
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))

            val uniqueImeis = mutableSetOf<String>()
            val resultList = ArrayList<DevBean>()

            // 2. 逐行处理
            reader.useLines { lines ->
                lines.forEach { line ->
                    if (line.contains("Commoninfo")) {
                        try {
                            val jsonObj = JSONObject(line)
                            val contentString = jsonObj.getString("content.content")
                            val contentObj = JSONObject(contentString)
                            val header = contentObj.getJSONObject("Header")
                            val array = header.getJSONArray("Commoninfo")
                            // 从 array 中获取第0个元素
                            val objString = array.getString(0)
                            val obj = JSONObject(objString)

                            val dev = obj.getString("dev")
                            val imei = obj.getString("imei")

                            // 3. IMEI去重处理
                            if (!uniqueImeis.contains(imei)) {
                                uniqueImeis.add(imei)
                                resultList.add(DevBean(dev, imei))
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            // 4. 生成输出文件

            if (resultList.isNotEmpty()) {
                val devSet = mutableSetOf<String>()
                val outputFile = File(context.cacheDir, "output.json")
                val jsonArray = JSONArray()

                resultList.forEach { bean ->
                    devSet.add(bean.dev)
                    if (!bean.dev.startsWith("ZYB_")) {
                        println("处理完毕 --- 非作业帮学习机型号: $devSet")
                    }
                    jsonArray.put(JSONObject().apply {
                        put("dev", bean.dev)
                        put("imei", bean.imei)
                    })
                }

                println("处理完毕 --- 数组大小: ${resultList.size}")
                // 输出 devSet 的所有数据
                println("处理完毕 --- 设备型号: $devSet")

                outputFile.writeText(jsonArray.toString())

                println("处理完毕 --- 输出文件路径: ${outputFile.absolutePath}")

                return uniqueImeis
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return mutableSetOf()
    }
}