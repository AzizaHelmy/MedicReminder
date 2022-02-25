//package com.example.medicationreminder.requests.view
//
//class health {
//
//FirebaseDatabase firebaseDatabase;
//DatabaseReference databaseReference;
//FirebaseRecyclerAdapter<Request, CustomRequestRow> firebaseRecyclerAdapter;
//FirebaseRecyclerOptions<Request> firebaseRecyclerOptions;
//RecyclerView.LayoutManager layoutManager;
//RecyclerView recyclerView;

//    private void init() {
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference(Common.Request);
//        Query query = databaseReference.orderByChild("receiverEmail").equalTo("mohamedhosameldien07@gmail,com");
//        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Request>()
//                .setQuery(query, Request.class)
//            .build();
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Request, CustomRequestRow>(firebaseRecyclerOptions) {
//
//            @Override
//            public int getItemViewType(int position) {
//                return firebaseRecyclerAdapter.getItem(position).isRequest() ? 1:0;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull CustomRequestRow holder, int position, @NonNull Request model) {
//
//                holder.senderName.setText(model.getSenderName());
//                holder.patientName.setText(model.getPatientName());
//                holder.Description.setText(model.getDescription());
//
//                holder.btnReject.setOnClickListener(View -> {
//                firebaseRecyclerAdapter.getRef(position).removeValue();
//            });
//
//                holder.btnAccept.setOnClickListener(view -> {
//                firebaseRecyclerAdapter.getRef(position)
//                    .child("request").setValue(true);
//            });
//
//            }
//
//            @NonNull
//            @Override
//            public CustomRequestRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view;
//                if(viewType == 0) {
//                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.requests_custom_row, null);
//                } else {
//                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, null);
//                }
//                return new CustomRequestRow(view);
//            }
//        };
//
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
//        firebaseRecyclerAdapter.startListening();
//
//    }
//
//
//}