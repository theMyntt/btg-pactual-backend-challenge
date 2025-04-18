package com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order;

import com.gabrielaraujo.order_processor_service.core.use_cases.create_new_order.io.CreateNewOrderUseCaseInput;

public abstract class CreateNewOrderUseCase {
    public abstract void execute(CreateNewOrderUseCaseInput input);
    protected abstract void applyInternalLogic(CreateNewOrderUseCaseInput input);
}
