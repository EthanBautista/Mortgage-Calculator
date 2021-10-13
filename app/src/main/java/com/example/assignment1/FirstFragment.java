package com.example.assignment1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment1.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.calculate.setOnClickListener(view1 -> {
            double mortgage = 0, interest = 0, period = 0;
            String mortgageText = binding.mortgage.getText().toString();
            String interestText = binding.interest.getText().toString();
            String periodText = binding.period.getText().toString();

            // Check if input is empty before parsing to double
            if (!checkEmpty(mortgageText)){
                mortgage = Double.parseDouble(mortgageText);
            }
            if (!checkEmpty(interestText)){
                interest = Double.parseDouble(interestText);
            }
            if (!checkEmpty(periodText)){
                period = Double.parseDouble(periodText)*12.00;
            }

            // Mortgage and period should be greater than 0, interest should be between 100% and 0%
            if(mortgage > 0 && interest <= 100 && interest > 0 && period > 0){
                interest =(interest/100.00)/12.00;
                //double emi = mortgage * interest * ((1 + interest)*period)/(((1 + interest)*period) - 1);
                double emi = mortgage * (interest * Math.pow(1+interest, period)/(Math.pow(1+interest, period)-1));

                // Show calculated EMI
                binding.emiLabel.setVisibility(View.VISIBLE);
                binding.emiText.setVisibility(View.VISIBLE);
                binding.emiText.setText(String.format("%.2f", emi));
            }else{
                Toast.makeText(getActivity(),"Enter valid values",Toast.LENGTH_SHORT).show();
            }

        });
    }

    // Check if text is empty and sends a Toast
    public boolean checkEmpty(String text){
        if (TextUtils.isEmpty(text)){
            Toast.makeText(getActivity(),"Fill missing values",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}