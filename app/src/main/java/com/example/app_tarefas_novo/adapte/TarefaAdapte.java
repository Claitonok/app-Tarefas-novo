package com.example.app_tarefas_novo.adapte;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_tarefas_novo.DB_Helper.TarefaDAO;
import com.example.app_tarefas_novo.MainActivity;
import com.example.app_tarefas_novo.R;
import com.example.app_tarefas_novo.model.Tarefa;
import com.google.android.material.snackbar.Snackbar;

import org.jspecify.annotations.NonNull;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;

public class TarefaAdapte extends RecyclerView.Adapter<MyViewHolder> {

    private List<Tarefa> listaTarefas = new ArrayList<>();

    public TarefaAdapte(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    public TarefaAdapte() {}

    public void setListaTarefas(List<Tarefa> listaTarefas){
        this.listaTarefas = listaTarefas;
    }

    @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);

            return new MyViewHolder(itemLista);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Tarefa tarefa = listaTarefas.get(position);
            holder.nomeTarefa.setText(tarefa.getNomeTarefa());

            holder.btnExcluir.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Confirmação")
                            .setMessage("Tem certeza que deseja excluir essa tarefa?")
                            .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    TarefaDAO dao = new TarefaDAO(view.getContext());
                                    boolean sucesso = dao.deletar(tarefa.getId());

                                    if(sucesso) {
                                        removerCliente(tarefa);
                                        Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }else{
                                        Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .create()
                            .show();
                }
            });


            holder.btnInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        // Obter o contexto a partir do holder
                        Context context = holder.itemView.getContext();

                        Toast.makeText(context,"Redirecionando!!!", Toast.LENGTH_LONG).show();

                        // Criar um Intent para iniciar a nova Activity
                        Intent intent = new Intent(context, MainActivity.class);

                        // Iniciar a nova Activity
                        context.startActivity(intent);
                }
            });


            //Opção  Editar atraves de um click no botao ImageButton!!!
            Tarefa tf = listaTarefas.get(position);
            holder.nomeTarefa.setText(tf.getNomeTarefa());
            holder.btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!tf.toString().isEmpty()){

                        //Minha variavel vindo do Front-End
                        String nTarefa = tf.toString();

                        //executar a ação salvar
                        TarefaDAO tarefaDAO = new TarefaDAO(view.getContext());

                        Tarefa tr = new Tarefa();
                        tr.setNomeTarefa(nTarefa);

                        boolean sucesso = tarefaDAO.atualizar(tr);

                        if(sucesso){
                            Tarefa tarefa = tarefaDAO.retornarUltimo();

                            adicionar(tarefa);

                            Toast.makeText(view.getContext(),
                                    "Sucesso ao editar a tarefa",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(),
                                    "Erro ao editar!",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Ops!!")
                                .setMessage("Por favor consulte a equipe tecnica?")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(view.getContext(),
                                                "Por favor consulte a equipe tecnica?!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton("Cancelar", null)
                                .create()
                                .show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
          return listaTarefas != null ? listaTarefas.size() : 0;
        }

    public void adicionar(Tarefa tarefa) {
        listaTarefas.add(tarefa);
        notifyItemInserted(getItemCount());
    }


    public void removerCliente(Tarefa tarefa){
       int id = listaTarefas.indexOf(tarefa);
        listaTarefas.remove(id);
        notifyItemRemoved(id);
    }

}

