package br.ucsal.pdm.unebrasil.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.model.Doador;

public class BeneficiarioCustomAdapter extends ListAdapter<Beneficiario, BeneficiarioCustomAdapter.CustomViewHolder> {

    public BeneficiarioCustomAdapter(@NonNull DiffUtil.ItemCallback<Beneficiario> diff) {
        super(diff);
    }

    @NonNull
    @Override
    public BeneficiarioCustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_doador_list, parent, false);
        return new BeneficiarioCustomAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeneficiarioCustomAdapter.CustomViewHolder holder, int position) {
        holder.getTextViewCPF().setText(getItem(position).getCpf());
        holder.getTextViewNome().setText(getItem(position).getNome());
        holder.getTextViewEmail().setText(getItem(position).getEmail());
        holder.getTextViewCelular().setText(getItem(position).getCelular());
        holder.getTextViewSenha().setText(getItem(position).getSenha());
        holder.getTextViewData().setText(getItem(position).getData());
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewCPF;
        private TextView textViewNome;
        private TextView textViewEmail;
        private TextView textViewCelular;
        private TextView textViewSenha;
        private TextView textViewData;

        public CustomViewHolder(@NonNull View view) {
            super(view);
//            textViewCPF = itemView.findViewById(R.id.cad_benef_input_et_cpf);
//            textViewNome = itemView.findViewById(R.id.main_doador_list_data);
//            textViewEmail = itemView.findViewById(R.id.main_doador_list_tipo);
//            textViewCelular = itemView.findViewById(R.id.main_doador_list_qtd);
//            textViewSenha = itemView.findViewById(R.id.main_doador_list_qtd);
//            textViewData = itemView.findViewById(R.id.main_doador_list_qtd);
        }

        public TextView getTextViewCPF() {
            return textViewCPF;
        }

        public TextView getTextViewNome() {
            return textViewNome;
        }

        public TextView getTextViewEmail() {
            return textViewEmail;
        }

        public TextView getTextViewCelular() {
            return textViewCelular;
        }

        public TextView getTextViewSenha() {
            return textViewSenha;
        }

        public TextView getTextViewData() {
            return textViewData;
        }
    }

    static class BeneficiarioDiff extends DiffUtil.ItemCallback<Beneficiario> {

        @Override
        public boolean areItemsTheSame(@NonNull Beneficiario oldItem, @NonNull Beneficiario newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Beneficiario oldItem, @NonNull Beneficiario newItem) {
            return (oldItem.getId() == newItem.getId());
        }
    }
}
