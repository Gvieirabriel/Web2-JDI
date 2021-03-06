/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao.impl;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gqueiroz
 */
public class ProdutoDAOImpl implements ProdutoDAO {

    Connection con = new ConnectionFactory().getConnection();

    @Override
    public List<Produto> listProduto() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM tb_produto");
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt(1));
                p.setNomeProduto(rs.getString(2));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Produto buscaProdutoPorId(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM tb_produto WHERE id_produto = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Produto> list = new ArrayList<Produto>();
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt(1));
                p.setNomeProduto(rs.getString(2));
                list.add(p);
            }
            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertProduto(Produto produto) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_produtos (nome_produto) VALUES (?)");
            st.setString(1, produto.getNomeProduto()); 
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeProduto(int idProduto) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("DELETE FROM tb_produto WHERE id_produto = ?");
            ps.setInt(1, idProduto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
