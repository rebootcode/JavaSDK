package io.cloudboost;

import io.cloudboost.util.UUID;
import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author cloudboost
 * 
 */

public class CloudUserTest {



	void initialize() {
		UTIL.init();
	}

	@Test(timeout = 20000)
	public void createNewUser() throws CloudException {
		final String username = PrivateMethod._makeString();
		final String passwd = "abcd";
		initialize();
		CloudUser obj = new CloudUser();
		obj.setUserName(username);
		obj.setPassword(passwd);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					Assert.assertEquals(object.get("username"), username);
				}
			}
		});
	}

	@Test(timeout = 20000)
	public void newUser() throws CloudException {
		final String username = PrivateMethod._makeString();
		final String passwd = "abcd";
		initialize();
		CloudUser obj = new CloudUser();
		obj.setUserName(username);
		obj.setPassword(passwd);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					Assert.assertEquals(object.get("username"), username);
				}
			}
		});
	}

	@Test(timeout = 20000)
	public void shouldCreateNewUser() throws CloudException {
		final String username = PrivateMethod._makeString();
		final String passwd = "abcd";
		initialize();
		CloudUser obj = new CloudUser();
		obj.setUserName(username);
		obj.setPassword(passwd);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					Assert.assertEquals(object.get("username"), username);
				}
			}
		});
	}

	@Test(timeout = 20000)
	public void shouldCreateNewUserWithVersion() throws CloudException {
		final String username = PrivateMethod._makeString();
		final String passwd = "abcd";
		initialize();
		CloudUser obj = new CloudUser();
		obj.setUserName(username);
		obj.setPassword(passwd);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					Assert.assertEquals(object.get("_version"), 0);
				}
			}
		});
	}

	@Test(timeout = 20000)
	public void loginLogoutUser() throws CloudException {
		initialize();
		CloudUser obj = new CloudUser();
		final String name = PrivateMethod._makeString();
		final String pass = PrivateMethod._makeString();
		obj.setUserName(name);
		obj.setPassword(pass);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				final CloudUser user = new CloudUser();
				user.setUserName(name);
				user.setPassword(pass);
				user.logIn(new CloudUserCallback(){

					@Override
					public void done(CloudUser object, CloudException e)throws CloudException {
						if(e != null){
								Assert.fail(e.getMessage());
						}
						
						if(object != null){
							if(object.get("username").equals(name)){
								CloudUser us=new CloudUser();
								us.set("username", name);
								us.set("password", pass);
								us.logOut(new CloudUserCallback(){
									@Override
									public void done(CloudUser x, CloudException t)	throws CloudException {

									}

						
								});
								
							}
							else{
								Assert.fail("logged in but different username returned");
							}
							
						}else{
							Assert.fail();
						}
					}
				});
			}
		});

	}
	@Test(timeout = 20000)
	public void shouldLogoutUser() throws CloudException {
		initialize();
		CloudUser obj = new CloudUser();
		final String name = PrivateMethod._makeString();
		final String pass = PrivateMethod._makeString();
		obj.setUserName(name);
		obj.setPassword(pass);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				final CloudUser user = new CloudUser();
				user.setUserName(name);
				user.setPassword(pass);
				user.logIn(new CloudUserCallback(){

					@Override
					public void done(CloudUser object, CloudException e)throws CloudException {
						if(e != null){
								Assert.fail(e.getMessage());
						}
						
						if(object != null){
							if(object.get("username").equals(name)){
								CloudUser us=new CloudUser();
								us.set("username", name);
								us.set("password", pass);
								us.logOut(new CloudUserCallback(){
									@Override
									public void done(CloudUser x, CloudException t)	throws CloudException {

									}

						
								});
								
							}
							else{
								Assert.fail("logged in but different username returned");
							}
							
						}else{
							Assert.fail();
						}
					}
				});
			}
		});

	}
	@Test(timeout = 20000)
	public void createUserGetVersion() throws CloudException {
		initialize();
		CloudUser user = new CloudUser();
		final String passwd = "abcd";
		final String newUser = PrivateMethod._makeString();
		user.setUserName(newUser);
		user.setPassword(passwd);
		user.setEmail(PrivateMethod._makeString() + "@abc.com");
		user.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}

				if (object != null) {
					if (object.getInteger("_version") >= 0) {
						Assert.assertEquals(object.get("username"), newUser);
					} else {
						Assert.fail("Create User Error");
					}

				}
			}
		});
	}

	@Test(timeout = 20000)
	public void queryOnUser() {

	}

	String roleName = PrivateMethod._makeString();

	@Test(timeout = 20000)
	public void createRole() throws CloudException {
		initialize();
		CloudRole role = new CloudRole(roleName);
		role.save(new CloudRoleCallback() {

			@Override
			public void done(CloudRole x, CloudException t)
					throws CloudException {
				if (t != null) {
					Assert.fail(t.getMessage());
				}

				if (x != null) {
					Assert.assertEquals(x.get("name"), roleName);
				} else {
					Assert.fail("Role Create Error");
				}
			}

		});
	}

	public void shouldDoQueryOnUser() throws CloudException {
		initialize();
		CloudUser obj = new CloudUser();
		final String uuid = PrivateMethod._makeString();
		final String passwd = "abcd";
		obj.setUserName(uuid);
		obj.setPassword(passwd);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(final CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					if (object.getInteger("_version") >= 0
							&& object.getUserName().equals(uuid)) {
						CloudQuery qry = new CloudQuery("User");
						qry.findById(object.getId(), new CloudObjectCallback() {

							@Override
							public void done(CloudObject x, CloudException t)
									throws CloudException {
								if (t != null)
									Assert.fail(t.getMessage());
								if (x != null)
									Assert.assertEquals(x.getId(),
											object.getId());

							}
						});
					}
					Assert.assertEquals(object.get("username"), uuid);
				}
			}
		});
	}

	@Test(timeout = 100000)
	public void assignRoleToUser() throws CloudException {
		initialize();
		CloudUser obj = new CloudUser();
		final String name = PrivateMethod._makeString();
		final String pass = PrivateMethod._makeString();
		obj.setUserName(name);
		obj.setPassword(pass);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					CloudUser user = new CloudUser();
					user.setUserName(name);
					user.setPassword(pass);
					user.logIn(new CloudUserCallback() {

						@Override
						public void done(final CloudUser newUser,
								CloudException t) throws CloudException {
							CloudRole role = new CloudRole(PrivateMethod
									._makeString());
							role.save(new CloudRoleCallback() {
								@Override
								public void done(CloudRole newRole,
										CloudException t) throws CloudException {
									if (t != null)
										Assert.fail(t.getMessage());
									newUser.addToRole(newRole,
											new CloudUserCallback() {

												@Override
												public void done(
														CloudUser anotherUser,
														CloudException e)
														throws CloudException {
													if (e != null) {
														Assert.fail(e
																.getMessage());
													}

													if (anotherUser != null) {
														Assert.assertEquals(
																anotherUser
																		.getUserName(),
																name);
													} else {
														Assert.fail("Add Role Error");
													}
												}

											});
								}

							});
						}

					});
				}
			}
		});

	}

	@Test(timeout = 20000)
	public void removeRoleFromUser() throws CloudException {
		initialize();
		CloudUser obj = new CloudUser();
		final String name = PrivateMethod._makeString();
		final String pass = PrivateMethod._makeString();
		obj.setUserName(name);
		obj.setPassword(pass);
		obj.setEmail(PrivateMethod._makeString() + "@abc.com");
		obj.signUp(new CloudUserCallback() {
			@Override
			public void done(CloudUser object, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}
				if (object != null) {
					CloudUser user = new CloudUser();
					user.setUserName(name);
					user.setPassword(pass);
					user.logIn(new CloudUserCallback() {

						@Override
						public void done(final CloudUser newUser,
								CloudException t) throws CloudException {
							CloudRole role = new CloudRole(PrivateMethod
									._makeString());
							role.save(new CloudRoleCallback() {
								@Override
								public void done(CloudRole newRole,
										CloudException t) throws CloudException {
									if (t != null)
										Assert.fail(t.getMessage());
									newUser.removeFromRole(newRole,
											new CloudUserCallback() {

												@Override
												public void done(
														CloudUser anotherUser,
														CloudException e)
														throws CloudException {
													if (e != null) {
														Assert.fail(e
																.getMessage());
													}

													if (anotherUser != null) {
														Assert.assertEquals(
																anotherUser
																		.getUserName(),
																name);
													} else {
														Assert.fail("Add Role Error");
													}
												}

											});
								}

							});
						}

					});
				}
			}
		});
	}

	@Test(timeout = 20000)
	public void encryptUserPassword() throws CloudException {
		initialize();
		final String username = PrivateMethod._makeString();
		final String passwd = "abcd";
		CloudUser user = new CloudUser();
		user.setUserName(username);
		user.setPassword(passwd);
		user.setEmail(PrivateMethod._makeString() + "@abc.com");
		user.save(new CloudObjectCallback() {

			@Override
			public void done(CloudObject newUser, CloudException e)
					throws CloudException {
				if (e != null) {
					Assert.fail(e.getMessage());
				}

				if (newUser != null) {
					if (newUser.get("password").equals(passwd)) {
						Assert.fail("Password is not encrypted");
					}
				}
			}
		});
	}

}