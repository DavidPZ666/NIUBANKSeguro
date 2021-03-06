package edu.escuelaing.niubank.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.escuelaing.niubank.controller.auth.LoginDto;
import edu.escuelaing.niubank.controller.auth.TokenDto;

import edu.escuelaing.niubank.data.User;
import edu.escuelaing.niubank.services.UserServicesImpl;

import edu.escuelaing.niubank.services.UserServicesImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v2/Banco")
public class UserController {
    public Gson gson = new Gson();

    public UserServicesImpl userServices = new UserServicesImpl();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String hello() {
        return gson.toJson("Hola");
    }

    @POST
    @Path("/Login/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TokenDto login(LoginDto loginDto) {
        return userServices.Login(loginDto);
    }


    @GET
    @Path("/infoUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User infoUser(@HeaderParam("authorization") String token) {
        return userServices.loadInfoUser(token);
    }


    @GET
    @Path("/verMonto/user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String verMonto(@HeaderParam("authorization") String token) throws Exception {
        return gson.toJson(userServices.verMonto(token));
    }

    @GET
    @Path("/verTransferencia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String verTransferencia(@HeaderParam("authorization") String token) {
        return gson.toJson(userServices.verTransferencias(token));
    }

    @GET
    @Path("/solicitar/{cedula}/{monto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String solicitarSobregiro(@PathParam("cedula") String cedula, @PathParam("monto") String monto) throws Exception {
        return gson.toJson(userServices.solicitarSobregiro(cedula, monto));
    }

    @GET
    @Path("/registrar/User/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String registrarUser(@HeaderParam("authorization") String token, @PathParam("cedula") String cedula) {
        System.out.println(cedula);
        return gson.toJson(userServices.registrarUser(token, cedula));
    }

    @PUT
    @Path("/crear/User/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String crearUser(@PathParam("cedula") String cedula, User user) {
        System.out.println(user.getIdentificador());
        return gson.toJson(userServices.crearUser(cedula, user));
    }

    @GET
    @Path("/modificarMonto/{cedula}/{monto}")
    public String modificarMonto(@HeaderParam("authorization")String token,
                                 @PathParam("cedula") String cedula,
                                 @PathParam("monto") String monto) throws Exception {
        return gson.toJson(userServices.modificarMonto(cedula,monto,token));

    }

    @GET
    @Path("/transferencia/{ccOrigen}/{ccDestino}/{monto}")
    public String transferencia(@PathParam("ccOrigen")String ccOrigen,
                                @PathParam("ccDestino")String ccDestino,
                                @PathParam("monto")String monto) throws Exception {

        return gson.toJson(userServices.transferencia(ccOrigen,ccDestino,monto));
    }

    @GET
    @Path("/autorizaciones")
    public String mostrarAutorizaciones() throws Exception {
        return gson.toJson(userServices.mostrarAutorizaciones());
    }


}
