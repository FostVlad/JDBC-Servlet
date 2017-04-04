package com.goloveschenko.system.command;

import com.goloveschenko.dao.controller.ProductController;
import com.goloveschenko.entities.purchases.Product;
import com.goloveschenko.system.receiver.Receiver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class AddProductCommand implements ICommand {
    private Receiver receiver;
    private HttpServletRequest request;

    public AddProductCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        long id = Long.parseLong(request.getParameter("id"));
        ProductController prodController = new ProductController();
        Product product = prodController.getProductByID(id);

        ArrayList<Product> prodForUser = (ArrayList<Product>) request.getSession().getAttribute("basket");
        if (prodForUser == null){
            prodForUser = new ArrayList<>();
        }
        prodForUser.add(product);
        request.getSession().setAttribute("basket", prodForUser);
        request.setAttribute("allProducts", prodController.getAllProducts());
        prodController.closeConnection();

        return receiver.acion(TypeCommand.ADD_PRODUCT);
    }
}
