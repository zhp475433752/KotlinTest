package com.zwwl.kotlintest.anim

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.zwwl.kotlintest.R


private const val ARG_PARAM1 = "param1"

class ShareFragment : Fragment() {
    private var resId: Int = 0
    private var activity:ShareSecondActivity? = null
    private var imageView:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            resId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as ShareSecondActivity?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_share, container, false)
        imageView = inflate.findViewById(R.id.fragment_share_image)
        imageView?.setImageResource(resId)

        imageView?.setOnClickListener(View.OnClickListener {
            activity?.supportFinishAfterTransition()
        })

        imageView?.getViewTreeObserver()
            ?.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    imageView?.getViewTreeObserver()?.removeOnPreDrawListener(this)
                    getActivity()?.supportStartPostponedEnterTransition()
                    return true
                }
            })

        return inflate
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int) =
            ShareFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, position)
                }
            }
    }

    fun getShareElement(): ImageView {
        return imageView!!
    }
}