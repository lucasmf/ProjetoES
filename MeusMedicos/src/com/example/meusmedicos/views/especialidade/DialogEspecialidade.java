package com.example.meusmedicos.views.especialidade;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meusmedicos.controllers.Controller;
import com.example.meusmedicos.R;
import com.example.meusmedicos.models.Especialidade;

public class DialogEspecialidade extends DialogFragment implements View.OnClickListener {
    Button adicionar, cancelar;
    EditText especialidade;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Nova especialidade");
        view = inflater.inflate(R.layout.dialog_especialidade, container, false);
        adicionar = (Button) view.findViewById(R.id.button3);
        cancelar= (Button) view.findViewById(R.id.button4);
        especialidade = (EditText) view.findViewById(R.id.editText15);
        adicionar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        especialidade.setOnClickListener(this);

        setCancelable(false);

        return view;
    }

    private void salvaEspecialidade(){
        final EditText especialidadeMessage = (EditText) view.findViewById(R.id.editText15);

        String especialidadeStr = especialidadeMessage.getText().toString();

        if (especialidadeStr.equals("")){
            Toast.makeText(getActivity(), "Especialidade não deve ser vazia!", Toast.LENGTH_LONG).show();
        } else {
            Controller.adicionaEspecialidade(new Especialidade(especialidadeMessage.getText().toString()));
            dismiss();
            Toast.makeText(getActivity(), "Especialidade adicionada com sucesso!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button3){
            salvaEspecialidade();
        } else if (view.getId() == R.id.button4) {
            dismiss();
            Toast.makeText(getActivity(), "Especialidade não adicionada!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        super.getActivity().recreate();
    }
}