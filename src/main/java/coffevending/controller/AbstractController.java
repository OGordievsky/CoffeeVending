package coffevending.controller;

import coffevending.service.CartService;
import coffevending.service.GoodsService;

public abstract class AbstractController {
    protected GoodsService goodsService = new GoodsService();
    protected CartService cartService = new CartService();
}