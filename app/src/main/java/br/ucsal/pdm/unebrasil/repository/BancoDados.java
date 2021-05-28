package br.ucsal.pdm.unebrasil.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.dao.BeneficiarioDAO;
import br.ucsal.pdm.unebrasil.repository.dao.DoacaoDAO;
import br.ucsal.pdm.unebrasil.repository.dao.DoadorDAO;

@Database(entities = {Beneficiario.class, Doacao.class, Doador.class}, version = 1, exportSchema = false)
public abstract class BancoDados extends RoomDatabase {

    public abstract DoacaoDAO getDoacaoDao();

    public abstract DoadorDAO getDoadorDao();

    public abstract BeneficiarioDAO getBeneficiarioDao();

    private static volatile BancoDados bancoDados = null;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BancoDados getInstance(Context context) {
        if (bancoDados == null) {
            synchronized (BancoDados.class) {
                if (bancoDados == null) {
                    bancoDados = Room.databaseBuilder(context, BancoDados.class, "banco")
                            .build();
                }
            }
        }
        return bancoDados;
    }
}