package com.artsearch.myproject.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.artsearch.myproject.App;
import com.artsearch.myproject.R;
import com.artsearch.myproject.MyContract;
import com.artsearch.myproject.MyUseCasePresenter;
import com.artsearch.myproject.adapter.ViewModelAdapter;
import com.artsearch.myproject.factory.GettyImageFactory;
import com.artsearch.myproject.view.ui.viewmodel.BaseViewModel;
import com.artsearch.myproject.util.SpaceItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;

public class MainFragment extends Fragment implements MyContract.PublishToView {


    private MyContract.ToPresenter forwardInteraction;

    @Inject
    ViewModelAdapter adapter;

    @Inject
    GettyImageFactory gettyImageFactory;

    @BindView(R.id.search_phrase)
    AutoCompleteTextView searchView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindDimen(R.dimen.image_space)
    int space;

    private Unbinder unbinder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().getAppComponent().inject(this);

        forwardInteraction = new MyUseCasePresenter(this, getContext(),
                gettyImageFactory);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

       /*Following 4 lines of code adds Artist-Name Suggestion(AutoComplete) Feature to SearchView from artistNamesSuggestion array*/
        String[] artistNames = getResources().getStringArray(R.array.artistNamesSuggestion);
        ArrayAdapter<String> adapterArtist = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.auto_complete, artistNames);
        searchView.setAdapter(adapterArtist);
        searchView.setThreshold(1);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new SpaceItemDecoration(space, space, space, space));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void showResult(List<BaseViewModel> viewModels) {
        progressBar.setVisibility(GONE);
        adapter.setViewModels(viewModels);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void showToastMessage(String message) {
        progressBar.setVisibility(GONE);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();

        searchView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                progressBar.setVisibility(View.VISIBLE);
                forwardInteraction.searchButtonClick(actionId, searchView.getText().toString());
                searchView.setText("");
                return true;
            }
            return false;
        });
    }

}
