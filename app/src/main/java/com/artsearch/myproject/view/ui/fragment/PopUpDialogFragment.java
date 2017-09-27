package com.artsearch.myproject.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.artsearch.myproject.App;
import com.artsearch.myproject.R;
import com.artsearch.myproject.MyPopUpContract;
import com.artsearch.myproject.PopUpUseCasePresenter;
import com.artsearch.myproject.api.model.ImageResult;
import com.artsearch.myproject.api.model.Metadata;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;


public class PopUpDialogFragment extends DialogFragment implements MyPopUpContract.PublishToPopUpView {


    private MyPopUpContract.popUpToPresenter forwardInterationPopUp;


    @BindView(R.id.image_view)
    ImageView mainImageView;


    @BindView(R.id.similar_image_view1)
    ImageView similarImageView1;


    @BindView(R.id.similar_image_view2)
    ImageView similarImageView2;


    @BindView(R.id.similar_image_view3)
    ImageView similarImageView3;


    @BindView(R.id.title_view)
    TextView titleViewTextView;

    @BindView(R.id.progress_bar1)
    ProgressBar progressBar;

    private Unbinder unbinder;


    public PopUpDialogFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog()
                .getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public static PopUpDialogFragment newInstanceOfPopUpDialogFragment(String id, String uri) {
        PopUpDialogFragment newInstance = new PopUpDialogFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("uri", uri);
        newInstance.setArguments(args);
        return newInstance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getAppComponent().inject(this);
        forwardInterationPopUp = new PopUpUseCasePresenter(this);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_popup, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String uri = getArguments().getString("uri");
        String id = getArguments().getString("id");
        getData(id, uri);

    }


    private void getData(String id, String uri) {
        progressBar.setVisibility(View.VISIBLE);
        forwardInterationPopUp.getDataPopUpDialog(id, uri);
        Picasso.with(getContext()).load(uri).fit().into(mainImageView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showSimilarImagesResult(List<ImageResult> similarImage) {
        Picasso.with(getContext()).load(similarImage.get(0).getDisplaySizes().get(0).getUri()).fit().into(similarImageView1);
        Picasso.with(getContext()).load(similarImage.get(1).getDisplaySizes().get(0).getUri()).fit().into(similarImageView2);
        Picasso.with(getContext()).load(similarImage.get(2).getDisplaySizes().get(0).getUri()).fit().into(similarImageView3);

        if (progressBar.isEnabled()) {
            progressBar.setVisibility(GONE);
        }
    }

    @Override
    public void showMetadataResult(List<Metadata> metadata) {
        titleViewTextView.append("Artist: " + metadata.get(0).getArtist());
        titleViewTextView.append("\n Title: " + metadata.get(0).getTitle());
        if (progressBar.isEnabled()) {
            progressBar.setVisibility(GONE);
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        if (progressBar.isEnabled()) {
            progressBar.setVisibility(GONE);
        }
    }
}
