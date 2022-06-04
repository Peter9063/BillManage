package DongYu.WebBase.System.ServiceImp;


import DongYu.WebBase.System.Entity.RoleUserRelation;
import DongYu.WebBase.System.Entity.SysBase.Sorte;
import DongYu.WebBase.System.Entity.SysBase.WebMessage;
import DongYu.WebBase.System.Entity.User;
import DongYu.WebBase.System.Mapping.RoleUserRelationMapper;
import DongYu.WebBase.System.Mapping.RowBounds;
import DongYu.WebBase.System.Mapping.UserMapper;
import DongYu.WebBase.System.Service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImp  implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private UserMapper mapping;

	@Autowired
	private RoleUserRelationMapper roleUserRelationMapper;

	@Override
	public Long getCount(User record) {
		return mapping.getCount(record);
	}


	@Override
	public List<User> findAllByRoleIds(User record, List<Long> roleIds) {
		return mapping.findAllByRoleId(record,roleIds);
	}

    @Override
    public List<User> findAllByRoleIds(User record,Long roleId, Integer type) {
        List<Long> list = new ArrayList<>();
		list.add(roleId);
        if (type != null && type == 2) {//return  has
            return this.findAllByRoleIds(record,list);
        }
        List<User> menuList = this.findAllByRoleIds(record,null);
        List<User> menuList1 = this.findAllByRoleIds(record,list);
        return this.getDifferent(menuList1, menuList);
    }

    /**
     *暂时注释 用下列测试
     * @param record
     * @param start
     * @param limit
     * @param sortes
     * @return
     */
//	@Override
//	public List<User> findPage(User record, Integer start, Integer limit, Sorte[] sortes) {
//		return mapping.findPage(record, new RowBounds(start,limit), Sorte.getSqlOrderStr(sortes));
//	}


    	@Override
	public List<User> findPage(User record, Integer start, Integer limit, Sorte[] sortes) {
            List<User> list= mapping.findPage(record, new RowBounds(start,limit), Sorte.getSqlOrderStr(sortes));
            for(User user1 : list){
                RoleUserRelation roleUserRelation = new RoleUserRelation();
                roleUserRelation.setUserId(user1.getId().longValue());
                List<RoleUserRelation > list1 = roleUserRelationMapper.findPage(roleUserRelation,null,null);
                List<String> roles= new ArrayList<>();
                for (RoleUserRelation roleUserRelation1 : list1) {
                   roles.add(roleUserRelation1.getRoleId().toString());
                }
                user1.setRoles(roles.toString());

            }
            return list;
    	}

	@Override
	public WebMessage<User[]> save(User[] records) {
		WebMessage<User[]> msg=new WebMessage<User[]>();
		if(records!=null){
			for(User item:records){
				if(item.getId()==null ||item.getId().equals(0)){
					mapping.insert(item);
				String[] roles=item.getRoles().split(",");
				for(int i =0 ;i < roles.length ; i++){
					RoleUserRelation roleUserRelation= new RoleUserRelation();
					roleUserRelation.setRoleId(Long.parseLong(roles[i]));
					roleUserRelation.setUserId(item.getId().longValue());
					roleUserRelationMapper.insert(roleUserRelation);
				}
				}
				else{
					mapping.update(item);
                        String[] roles=item.getRoles().split(",");
                        RoleUserRelation roleUserRelation = new RoleUserRelation();
                        roleUserRelation.setUserId(item.getId().longValue());
                        List<RoleUserRelation> list =roleUserRelationMapper.findPage(roleUserRelation,null,null);

                       Map<String,Integer> map = new HashMap<>();

                        for(RoleUserRelation roleUserRelation1 : list){
                           map.put(roleUserRelation1.getRoleId().toString(),1);
                        }
                        for(int i=0 ;i< roles.length; i++){
                            if(map.get(roles[i])==null){
                              RoleUserRelation roleUserRelation1= new RoleUserRelation();
                              roleUserRelation1.setUserId(item.getId().longValue());
                              roleUserRelation1.setRoleId(Long.valueOf(roles[i]));
                              roleUserRelationMapper.insert(roleUserRelation1);
                            }
                        }

                        map.clear();
                    for(int i=0 ;i< roles.length; i++){
                        map.put(roles[i],1);
                    }
                    for(RoleUserRelation roleUserRelation1 : list)
                        if (map.get(roleUserRelation1.getRoleId().toString()) == null) {
                            RoleUserRelation roleUserRelation2 = new RoleUserRelation();
                            roleUserRelation2.setUserId(item.getId().longValue());
                            roleUserRelation2.setRoleId(Long.valueOf(roleUserRelation1.getRoleId()));
                            roleUserRelationMapper.deleteByRoleIdAndUserId(roleUserRelation2);
                        }



				}
			}
			msg.setSuccess(true);
			msg.setMessage("保存成功!");
		}
		return msg;
	}

	@Override
	public WebMessage<User> delete(User[] records) {
		WebMessage<User> msg=new WebMessage<User>();
		if(records!=null){
			for(User item:records){
				mapping.deleteById(item);
			}
			msg.setSuccess(true);
			msg.setMessage("删除成功!");
		}
		return msg;
	}

//	/**
//	 * 密码修改
//	 * @param records
//	 * @return
//	 */
//	@Override
//	public Message<User> modifPassword(User records) {
//		Message<User> msg=new Message<>();
//		User condition=new User();
//		if(records!=null){
//			condition.setUserName(records.getUserName());
//			condition.setPassWord(records.getPassWord());
//		}
//		List<User> data=this.findPage(condition, 0, Integer.MAX_VALUE,null);
//		String newPassWord=records.getNewPassWord();
//		if(data.size()>0&&newPassWord!=""&&newPassWord!=null){
//			condition.setPassWord(newPassWord);
//			condition.setId(data.get(0).getId());
//			mapping.update(condition);
//			msg.Success=1;
//			msg.setMsg("修改成功");
//		}else {
//			msg.Success=2;
//			msg.setMsg("用户或密码不正确");
//		}
//		return msg;
//	}

//	@Override
//	public WebMessage<User> ediitPassword(User records) {
//		WebMessage<User> msg=new WebMessage<>();
//		Message message=this.modifPassword(records);
//		if(message.Success==1){
//			msg.setSuccess(true);
//		}else {
//			msg.setSuccess(false);
//		}
//		msg.setMessage(message.getMsg());
//
//
//		return msg;
//	}

    @Override
    public List<User> getDifferent(List<User> list1, List<User> list2) {
        List<User> diff = new ArrayList<>();
        Map<User, Integer> map = new HashMap<>();
        for (User user : list1) {
            map.put(user, 1);
        }
        for (User user : list2) {
//        	System.err.println(map.get(user));
            if (map.get(user) == null) {
                diff.add(user);
            }
        }
        return diff;
    }




}
