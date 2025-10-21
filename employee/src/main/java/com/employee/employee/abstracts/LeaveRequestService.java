package com.employee.employee.abstracts;

import java.util.List;

import com.employee.employee.dtos.LeaveRequestCreate;
import com.employee.employee.entities.LeaveRequest;

public interface LeaveRequestService {
    LeaveRequest createOne(LeaveRequestCreate leaveRequest, Long employeeId);

    List<LeaveRequest> getAllByEmployeeId(Long employeeId);
}