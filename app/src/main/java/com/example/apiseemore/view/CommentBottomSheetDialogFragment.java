package com.example.apiseemore.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.apiseemore.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CommentBottomSheetDialogFragment extends BottomSheetDialogFragment {

    //private  bottomSheetBehavior;
    private TextView txtCheckA;
    private TextView txtCheckB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_bottom_sheet_layout,container,false);

        txtCheckA = view.findViewById(R.id.txtCheckA);
        txtCheckB = view.findViewById(R.id.txtCheckB);
        txtCheckA.setVisibility(View.INVISIBLE);
        txtCheckA.setText("A Can be Change");
        txtCheckB.setText("B Can be Change");
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCheckA = view.findViewById(R.id.txtCheckA);
        txtCheckB = view.findViewById(R.id.txtCheckB);
        txtCheckA.setVisibility(View.INVISIBLE);
        txtCheckA.setText("A Can be Change");
        txtCheckB.setText("B Can be Change");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.comment_bottom_sheet_layout, null);
        bottomSheet.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));
        //note
        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        return bottomSheet;
    }
}