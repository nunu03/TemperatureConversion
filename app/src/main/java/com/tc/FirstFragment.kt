package com.tc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tc.databinding.FragmentFirstBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var checkId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConvert.setOnClickListener {
            val degrees = binding.textviewDegreesValue.text
            if (degrees.isEmpty()) {
                return@setOnClickListener
            }
            if (checkId == 0) {
                val tf = degrees.toString().replace("°C","").toInt() * 9 / 5 + 32
                binding.textviewResultValue.setText("$tf°F")
            } else if (checkId == 1) {
                val tf = degrees.toString().replace("°C","").toInt() + 273.15
                binding.textviewResultValue.setText(tf.toString()+"K")
            }
        }
        binding.textviewTypeValueFahrenheit.isChecked = true
        binding.textviewTypeValue.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                binding.textviewTypeValueFahrenheit.id -> {
                    checkId = 0
                }
                binding.textviewTypeValueKelvin.id -> {
                    checkId = 1
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}