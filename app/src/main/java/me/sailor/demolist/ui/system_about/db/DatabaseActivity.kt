package me.sailor.demolist.ui.system_about.db

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_database.*
import me.sailor.demolist.R
import me.sailor.libcommon.manager.executor.ExecutorManager

class DatabaseActivity : AppCompatActivity() {
    //修改一下
    private var users: MutableList<User>? = ArrayList()
    var mAdapter: DBRvAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        DBManager.getInstance().init(application, "testDB1")
        add.setOnClickListener(listener)
        update.setOnClickListener(listener)
        del.setOnClickListener(listener)
        look.setOnClickListener(listener)
        mAdapter = DBRvAdapter(users)
        rv_db.layoutManager = LinearLayoutManager(this)
        rv_db.adapter = mAdapter
    }

    private var listener: View.OnClickListener = View.OnClickListener {

        when (it) {
            add -> {
                Log.d("DatabaseActivity", "add")
                ExecutorManager.getInstance().singleIO().execute {
                    DBManager.getInstance().addUser(getUsr())
                }
            }
            update -> {
                Log.d("DatabaseActivity", "update")
                ExecutorManager.getInstance().singleIO().execute {
                    DBManager.getInstance().update(getUsr())
                }
            }
            del -> {
                Log.d("DatabaseActivity", "del")
                ExecutorManager.getInstance().singleIO().execute {
                    DBManager.getInstance().delUser(i)
                }
            }
        }
        update()
    }


    private fun update() {
        ExecutorManager.getInstance().singleIO().execute {
            var list: List<User> = DBManager.getInstance().queryUser()
            users?.clear()
            users?.addAll(list)
            runOnUiThread {
                mAdapter?.notifyDataSetChanged()
            }
        }
    }


    var i: Int = (Math.random() * 10000).toInt()
    private fun getUsr(): User {

        var user = User()
        user.id = i
        user.age = 20 + i
        user.name = "smida$i"
        return user
    }
}
