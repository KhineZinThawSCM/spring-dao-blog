package springblog.bl.services.role.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springblog.bl.dto.RoleDTO;
import springblog.bl.services.role.RoleService;
import springblog.persistence.dao.role.RoleDao;
import springblog.persistence.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = this.roleDao.getAllRoles();
        List<RoleDTO> roleDTOList = roles.stream().map(role -> {
            RoleDTO roleDTO = new RoleDTO(role);
            return roleDTO;
        }).collect(Collectors.toList());
        return roleDTOList;
    }

}
