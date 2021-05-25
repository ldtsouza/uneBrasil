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

public class ListaBenefCustomAdapter extends ListAdapter<Beneficiario, ListaBenefCustomAdapter.CustomViewHolder> {

    public ListaBenefCustomAdapter(@NonNull DiffUtil.ItemCallback<Beneficiario> diff) {
        super(diff);
    }

    @NonNull
    @Override
    public ListaBenefCustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_beneficiario_list, parent, false);
        return new ListaBenefCustomAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaBenefCustomAdapter.CustomViewHolder holder, int position) {
        holder.getTextViewId().setText(String.valueOf(getItem(position).getId()));
        holder.getTextViewBeneficiario().setText(getItem(position).getNome());
        holder.getTextViewTelefone().setText(getItem(position).getCelular());
        holder.getTextViewEmail().setText(getItem(position).getEmail());
        //holder.getTextViewDoacoesCestas().setText(getItem(position));
        //holder.getTextViewDoacoesKit().setText(getItem(position));
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewId;
        private TextView textViewBeneficiario;
        private TextView textViewTelefone;
        private TextView textViewEmail;
        private TextView textViewDoacoesCestas;
        private TextView textViewDoacoesKit;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            textViewId = itemView.findViewById(R.id.main_benef_list_id);
            textViewBeneficiario = itemView.findViewById(R.id.main_benef_list_benef);
            textViewTelefone = itemView.findViewById(R.id.main_benef_list_telefone);
            textViewEmail = itemView.findViewById(R.id.main_benef_list_email);
            textViewDoacoesCestas = itemView.findViewById(R.id.main_benef_list_qtdCestas);
            textViewDoacoesKit = itemView.findViewById(R.id.main_benef_list_qtdKit);
        }

        public TextView getTextViewId() {
            return textViewId;
        }

        public TextView getTextViewBeneficiario() {
            return textViewBeneficiario;
        }

        public TextView getTextViewTelefone() {
            return textViewTelefone;
        }

        public TextView getTextViewEmail() {
            return textViewEmail;
        }

        public TextView getTextViewDoacoesCestas() {
            return textViewDoacoesCestas;
        }

        public TextView getTextViewDoacoesKit() {
            return textViewDoacoesKit;
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