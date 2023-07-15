package com.uniminuto.appcentroprogresa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView nameUser, typeCareer, Email;
    FirebaseFirestore mFirestore;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mFirestore = FirebaseFirestore.getInstance();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Verificar si hay un usuario actualmente autenticado
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();

            // Obtener la referencia del documento del usuario actual
            DocumentReference userRef = db.collection("user").document(userId);

            userRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                // El documento del usuario existe, puedes obtener los datos
                                String titleClassification = documentSnapshot.getString("name");
                                String nameCareer = documentSnapshot.getString("career");
                                String nameEmail = documentSnapshot.getString("email");
                                // Haz algo con los datos del usuario
                                nameUser.setText(titleClassification);
                                typeCareer.setText(nameCareer);
                                Email.setText(nameEmail);
                            } else {
                                // El documento del usuario no existe
                                Toast.makeText(getContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error al obtener los datos del usuario
                            Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // No hay un usuario autenticado
            Toast.makeText(getContext(), "No hay usuario autenticado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_profile, container, false);

        nameUser = rootview.findViewById(R.id.textViewTitleClassification);
        typeCareer = rootview.findViewById(R.id.textViewNameCareer);
        Email = rootview.findViewById(R.id.textViewNameEmail);
        // Inflate the layout for this fragment

        return rootview;
    }
}