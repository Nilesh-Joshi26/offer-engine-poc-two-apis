package com.hdfcbank.offerengine.domain.port.out;

import com.hdfcbank.offerengine.domain.model.Label;

import java.util.Optional;

public interface LabelRepositoryPort {

    Optional<Label> findByLabelCode(String labelCode);

    boolean existsByLabelCode(String labelCode);

    Label save(Label label);
}
