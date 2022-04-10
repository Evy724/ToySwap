package com.revature.project2.viewmodel

import androidx.lifecycle.ViewModel
import com.revature.project2.model.api.tradeoffers.ResponseUserOffers
import com.revature.project2.model.api.tradeoffers.TradeOffer

class TradeOffersViewModel: ViewModel()
{
    var tradeOffer: ResponseUserOffers? = null
}