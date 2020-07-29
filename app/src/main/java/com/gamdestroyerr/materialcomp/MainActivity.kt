package com.gamdestroyerr.materialcomp

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_container.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var isOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var animator: ObjectAnimator

        alertbtn.setOnClickListener {
            fab.hide()
            editFab.hide()
            contactFab.hide()
            categoryFab.hide()
            txt_cat.visibility = View.INVISIBLE
            txt_contact.visibility = View.INVISIBLE
            txt_edit.visibility = View.INVISIBLE
            animator = ObjectAnimator.ofFloat(fab,
                "Rotation",
                0f
            )
            animator.duration = 400
            animator.start()
            isOpen = false
            val factory = LayoutInflater.from(this)
            val view1: View = factory.inflate(R.layout.custom_dialog_container, null)
            //inflating the layout into the view and passing the view to show dialog fun

            if(Build.VERSION.SDK_INT >=22){
                GlobalScope.launch {
                delay(800)
                alertbtn.visibility = View.INVISIBLE
                }
            }
            showDailog(it, view1)
        }

        fab.setOnClickListener {

            animator = ObjectAnimator.ofFloat(fab,
                "Rotation",
                135f
            )
            animator.duration = 400
            animator.start()

            if (isOpen){

                editFab.hide()
                contactFab.hide()
                categoryFab.hide()
                txt_cat.visibility = View.INVISIBLE
                txt_contact.visibility = View.INVISIBLE
                txt_edit.visibility = View.INVISIBLE
                animator = ObjectAnimator.ofFloat(fab,
                    "Rotation",
                    0f
                )
                animator.duration = 400
                animator.start()

                isOpen = false

            } else {

                editFab.show()
                contactFab.show()
                categoryFab.show()
                txt_cat.visibility = View.VISIBLE
                txt_contact.visibility = View.VISIBLE
                txt_edit.visibility = View.VISIBLE
                animator = ObjectAnimator.ofFloat(fab,
                    "Rotation",
                    135f
                )
                animator.duration = 400
                animator.start()

                isOpen = true
            }
        }


    }

    fun showDailog(view: View, view1: View){

        val dialog = MaterialAlertDialogBuilder(this)
            .setView(view1)
            .setCancelable(false)
            .setBackground(getDrawable(R.drawable.custom_dialog))
            .show()

        view1.confirm_button.setOnClickListener {
            alertbtn.visibility = View.VISIBLE
            var animator = ObjectAnimator.ofFloat(fab,
                "TranslationY",
                (-32f).toDp(this)
            )
            animator.duration = 180
            animator.start()

            Snackbar.make(view, "Custom Dialog is displayed", Snackbar.LENGTH_SHORT)
                .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        animator = ObjectAnimator.ofFloat(fab,
                            "TranslationY",
                            0f
                        )
                        animator.duration = 210
                        animator.start()

                    }
                }).show()
            fab.show()
            dialog.dismiss()
        }

        view1.cancel_button.setOnClickListener {
            alertbtn.visibility = View.VISIBLE
            fab.show()
            dialog.dismiss()
        }


        //old Method..........................................................................

//            .setNeutralButton("CANCEL") { dialogInterface, _ ->
//                dialogInterface.dismiss()
//            }
//            .setPositiveButton("CONFIRM") { dialogInterface, _ ->
//
//                alertbtn.visibility = View.VISIBLE
//                var animator = ObjectAnimator.ofFloat(fab,
//                    "TranslationY",
//                    (-32f).toDp(this)
//                )
//                animator.duration = 180
//                animator.start()
//
//                Snackbar.make(view, "Custom Dialog is displayed", Snackbar.LENGTH_SHORT)
//                    .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>(){
//
//                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
//                            super.onDismissed(transientBottomBar, event)
//                            animator = ObjectAnimator.ofFloat(fab,
//                                "TranslationY",
//                                0f
//                            )
//                            animator.duration = 210
//                            animator.start()
//
//                        }
//                    }).show()
//                dialogInterface.dismiss()
//            }

        //old method....................................................................

    }

    fun Float.toDp(context: MainActivity): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    )
}