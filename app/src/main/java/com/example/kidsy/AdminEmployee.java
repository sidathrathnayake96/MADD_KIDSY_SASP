package com.example.kidsy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AdminEmployee extends AppCompatActivity {

    EditText txtId,txtName,txtPhone,txtSalary;
    Button btnSave,btnShow,btnUpdate,btnDelete;
    DatabaseReference dbRef;
    Employee emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee);

        txtId=findViewById(R.id.emp_id_et);
        txtName=findViewById(R.id.emp_name_et);
        txtPhone=findViewById(R.id.emp_phone_et);
        txtSalary=findViewById(R.id.emp_salary_et);

        btnSave=findViewById(R.id.ebtnSave);
        btnShow=findViewById(R.id.ebtnshow);
        btnUpdate=findViewById(R.id.ebtnupdate);
        btnDelete=findViewById(R.id.ebtndelete);

        emp = new Employee();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Employee").child("Emp1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(),"Successfully deleted", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Employee").child("Emp1").child("name").setValue(txtName.getText().toString().trim());
                dbRef.child("Employee/Emp1/salary").setValue(txtSalary.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"Successfully Updated Employee Details", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Employee/Emp1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()){
                            txtId.setText(snapshot.child("id").getValue().toString());
                            txtName.setText(snapshot.child("name").getValue().toString());
                            txtPhone.setText(snapshot.child("phone").getValue().toString());
                            txtSalary.setText(snapshot.child("salary").getValue().toString());
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Cannot find Emp1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef= FirebaseDatabase.getInstance().getReference().child("Employee");
                try {
                    if (TextUtils.isEmpty(txtId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Employee ID", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Employee Name", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtPhone.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Employee Contact Number", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtSalary.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter Employee Salary", Toast.LENGTH_SHORT).show();

                    else {
                        emp.setId(txtId.getText().toString().trim());
                        emp.setName(txtName.getText().toString().trim());
                        emp.setPhone(Integer.parseInt(txtPhone.getText().toString().trim()));
                        emp.setSalary(txtSalary.getText().toString().trim());

                        dbRef.child("Emp1").setValue(emp);
                        Toast.makeText(getApplicationContext(), "Employee Successfully added", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls() {
        txtId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtSalary.setText("");
    }
}