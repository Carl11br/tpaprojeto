package com.mycompany.tpaprojeto.persistence;

import com.mycompany.tpaprojeto.model.Caixa;
import com.mycompany.tpaprojeto.model.Compra;
import com.mycompany.tpaprojeto.model.Gerente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

public class CompraPersitence {

    private static HashMap<Integer, Compra> compras;
    private int ultimoId = 0;

    public CompraPersitence() {
        if (this.lerComprasDoArquivo() == false) {
            this.compras = new HashMap<>();
        } else {
            atualizarUltimoId();
        }
    }

    private void atualizarUltimoId() {
        for (HashMap.Entry<Integer, Compra> ele : compras.entrySet()) {
            this.ultimoId = ele.getKey();
        }
    }

    public boolean salvarComprasNoArquivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("compras.bin"));
            oos.writeObject(compras);
            oos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean lerComprasDoArquivo() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("compras.bin"));
            this.compras = (HashMap<Integer, Compra>) ois.readObject();
            ois.close();
            return true;
        } catch (IOException ex) {

            return false;
        } catch (ClassNotFoundException ex) {
            return false;
        }

    }

    public boolean adicionarCompraNoArquivo(Compra comp) {
        ultimoId += 1;
        compras.put(ultimoId, comp);
        return this.salvarComprasNoArquivo();
    }

    public boolean deletarCompraDoArquivo(int id) {
        Compra c;
        boolean eraUltimoId = false;
        if (compras.keySet().contains(id)) {
            if (id == ultimoId) {
                atualizarUltimoId();
                eraUltimoId = true;
                c = compras.remove(id);
            } else {
                c = compras.remove(id);
            }
            if (this.salvarComprasNoArquivo()) {
                return true;
            } else { // Se não foi possível salvar no arquivo, devolve a compra removida do hashmap e volta o valor do ultimoId, caso ele tenha sido atualizado
                compras.put(id, c);
                if (eraUltimoId) {
                    ultimoId = id;
                }
            }
        }

        return false;

    }

    public int deletarComprasAntigasDoArquivo(int n) {
        int count = 0;
        if (n >= compras.size()) {
            count = compras.size();
            compras.clear();

        } else {
            for (Iterator it = compras.keySet().iterator(); it.hasNext();) {
                if (count == n) {
                    break;
                }
                it.next();
                it.remove();
                count++;

            }
        }
        atualizarUltimoId();
        salvarComprasNoArquivo();
        return count;
    }

    public Compra buscarCompraNoArquivo(int id) {
        if (compras.keySet().contains(id)) {
            return compras.get(id);
        } else {
            return null;
        }
    }

    public HashMap<Integer, Compra> getCompras() {
        return compras;
    }
}
