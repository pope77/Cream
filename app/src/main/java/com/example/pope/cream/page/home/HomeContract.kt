package com.example.pope.cream.page.home

import com.example.pope.cream.page.base.BasePresenter
import com.example.pope.cream.page.base.BaseView

interface HomeContract{

    interface CreamPresenter:BasePresenter

    interface  CreamView:BaseView<CreamPresenter>

    interface CollectionPresenter:BasePresenter

    interface CollectionView:BaseView<CollectionPresenter>

    interface CenterPresenter:BasePresenter

    interface CenterView:BaseView<CenterPresenter>

}
