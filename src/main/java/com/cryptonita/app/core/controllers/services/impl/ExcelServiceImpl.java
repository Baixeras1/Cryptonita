package com.cryptonita.app.core.controllers.services.impl;

import com.cryptonita.app.data.providers.IUserProvider;
import com.cryptonita.app.dto.data.response.UserResponseDTO;
import com.cryptonita.app.security.SecurityContextHelper;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExcelServiceImpl extends AbstractXlsView {

    private final IUserProvider userProvider;
    private final SecurityContextHelper securityContextHelper;
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserResponseDTO userResponseDTO = userProvider.getByName(securityContextHelper.getUser().username);
        response.setHeader("Content-Disposition","attachment; filename=\"" + userResponseDTO + ".xlsx\"");
        Sheet hoja = workbook.createSheet("Clientes");
    }
}
