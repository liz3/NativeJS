package de.liz3.nativejs.bridge

import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by liz3 on 22.06.17.
 */

class JsonFuncs {

    fun encode(node:Node) : String {

        val json = JSONObject()

        if(node.value is Node) {

            json.put(node.key, parseNode(node))

        } else if(node.value is List) {

        } else {
            json.put(node.key, node.value)
        }

        return json.toString()
    }

    fun encode(list:List) {

    }

    private fun parseNode(node:Node) : JSONObject{
        val obj = JSONObject()

        if(node.value is Node) {
            obj.put(node.key, parseNode(node))
        } else if(node.value is List) {
            obj.put(node.key, parseList(node.value.objects))
        } else {
            obj.put(node.key, node.value)
        }

        return obj
    }
    private fun parseList(list: Vector<Any>) : JSONArray {
        val obj = JSONArray()
        for(item in list) {

            if(item is Node) {
                obj.put(parseNode(item))
            } else if(item is List) {
                obj.put(parseList(item.objects))
            } else {
                obj.put(item)
            }
        }
        return obj
    }

}
class Node(val key:String, val value:Any)

class List(val objects:Vector<Any>)