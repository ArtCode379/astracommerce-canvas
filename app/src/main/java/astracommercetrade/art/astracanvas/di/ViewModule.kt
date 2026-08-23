package astracommercetrade.art.astracanvas.di

import astracommercetrade.art.astracanvas.ui.viewmodel.AppViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.CartViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.CheckoutViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.JVONGOnboardingVM
import astracommercetrade.art.astracanvas.ui.viewmodel.OrderViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.ProductDetailsViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.ProductViewModel
import astracommercetrade.art.astracanvas.ui.viewmodel.JVONGSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        JVONGSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        JVONGOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}