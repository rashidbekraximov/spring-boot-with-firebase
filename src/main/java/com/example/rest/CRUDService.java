package com.example.rest;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.protobuf.Api;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFireStore.collection("users").document(crud.getName()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateCRUD(CRUD crud) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(crud.getName()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public CRUD getCRUD(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection("users").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CRUD crud;
        if (document.exists()) {
            crud = document.toObject(CRUD.class);
            return crud;
        }
        return null;
    }

    public String deleteCRUD(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("users").document(documentId).delete();
        return "successfully deleted" + documentId;
    }


}
